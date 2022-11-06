package eu.trentorise.game.repo;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.ListUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.util.JSONParseException;

import eu.trentorise.game.core.LogHub;
import eu.trentorise.game.managers.ClassificationUtils;
import eu.trentorise.game.model.ChallengeConcept;
import eu.trentorise.game.model.Game;
import eu.trentorise.game.model.PointConcept.PeriodInstance;
import eu.trentorise.game.model.core.ComplexSearchQuery;
import eu.trentorise.game.model.core.ComplexSearchQuery.QueryElement;
import eu.trentorise.game.model.core.ComplexSearchQuery.SearchElement;
import eu.trentorise.game.model.core.ComplexSearchQuery.StructuredElement;
import eu.trentorise.game.model.core.ComplexSearchQuery.StructuredSortItem;
import eu.trentorise.game.model.core.RawSearchQuery;
import eu.trentorise.game.model.core.RawSearchQuery.Projection;
import eu.trentorise.game.model.core.RawSearchQuery.SortItem;
import eu.trentorise.game.model.core.StringSearchQuery;
import eu.trentorise.game.services.GameService;

public class PlayerRepoImpl implements ExtendPlayerRepo {

	private static final Logger logger = LoggerFactory.getLogger(PlayerRepoImpl.class);
	@Autowired
	private MongoTemplate mongo;

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private GameService gameSrv;

	@Override
	public Page<StatePersistence> search(String gameId, RawSearchQuery query, Pageable pageable) {
		List<StatePersistence> result = null;
		BasicQuery q = null;
		String queryString = "{}";
		String projection = null;
		try {
			if (query != null) {
				if (query.getQuery() != null) {
					queryString = mapper.writeValueAsString(query.getQuery());
				}
				projection = addProjection(query.getProjection());
			}
			q = new BasicQuery(queryString, projection);
			q = (BasicQuery) addSort(q, query != null ? query.getSortItems() : null);
			q.with(pageable);

			if (gameId != null) {
				q.addCriteria(Criteria.where("gameId").is(gameId));
			}

			result = mongo.find(q, StatePersistence.class);

		} catch (JSONParseException | UncategorizedMongoDbException | MongoException | JsonProcessingException e) {
			exceptionHandler(gameId, e);
		}

		long totalSize = mongo.count(q, StatePersistence.class);
		return new PageImpl<>(result, pageable, totalSize);
	}

	@Override
	public Page<StatePersistence> search(String gameId, ComplexSearchQuery query, Pageable pageable) {
		List<StatePersistence> result = null;
		BasicQuery q = null;
		String queryString = "{}";
		String projection = null;
		StringBuffer buffer = new StringBuffer();
		Game game = gameSrv.loadGameDefinitionById(gameId);
		if (query != null) {
			Map<String, List<QueryElement>> parts = query.getQuery();
			if (parts != null) {
				for (SearchElement element : ComplexSearchQuery.SearchElement.values()) {
					List<QueryElement> queryParts = parts.get(element.getDisplayName());
					if (queryParts != null) {
						buffer.append(queryPartToString(game, element.getDisplayName(), queryParts));
					}
				}
				if (buffer.length() > 0) {
					queryString = buffer.toString();
				} else {
					throw new IllegalArgumentException("Query seems to be not valid: searchElements not valid");
				}

			}

			projection = addProjection(game, query.getProjection());
		}
		q = new BasicQuery(queryString, projection);
		q = (BasicQuery) addStructuredSort(game, q, query != null ? query.getSortItems() : null);
		q.with(pageable);
		if (gameId != null) {
			q.addCriteria(Criteria.where("gameId").is(gameId));
		}
		try {
			result = mongo.find(q, StatePersistence.class);
		} catch (JSONParseException | UncategorizedMongoDbException | MongoException e) {
			exceptionHandler(gameId, e);
		}
		long totalSize = mongo.count(q, StatePersistence.class);
		return new PageImpl<>(result, pageable, totalSize);
	}

	@Override
	public Page<StatePersistence> search(String gameId, StringSearchQuery query, Pageable pageable) {
		List<StatePersistence> result = null;
		String queryString = "{}";
		if (query != null && query.getQuery() != null) {
			queryString = query.getQuery();
		}

		BasicQuery q = new BasicQuery(queryString);
		q.with(pageable);
		if (gameId != null) {
			q.addCriteria(Criteria.where("gameId").is(gameId));
		}
		try {
			result = mongo.find(q, StatePersistence.class);
		} catch (JSONParseException | UncategorizedMongoDbException | MongoException e) {
			exceptionHandler(gameId, e);
		}
		long totalSize = mongo.count(q, StatePersistence.class);
		return new PageImpl<>(result, pageable, totalSize);

	}

	private String addProjection(Projection proj) {
		String result = null;
		if (proj != null) {
			List<String> projectionIncludeFields = ListUtils.emptyIfNull(proj.getIncludeFields());
			List<String> projectionExcludeFields = ListUtils.emptyIfNull(proj.getExcludeFields());
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");

			for (String includeField : projectionIncludeFields) {
				if (buffer.length() > 1) {
					buffer.append(",");
				}
				buffer.append(String.format("\"%s\":1", includeField));
				// append type field when necessary to permit correct creation
				// of
				// instance of class concept
				String typeField = appendTypeField(includeField);
				if (typeField != null) {
					buffer.append(String.format(",\"%s\":1", typeField));
				}

			}

			for (String excludedField : projectionExcludeFields) {
				if (buffer.length() > 1) {
					buffer.append(",");
				}
				buffer.append(String.format("\"%s\":0", excludedField));
			}
			buffer.append("}");
			result = buffer.toString();
		}

		return result;
	}

	private String addProjection(Game game, ComplexSearchQuery.StructuredProjection proj) {
		String result = null;
		if (proj != null) {
			List<StructuredElement> projectionIncludeFields = ListUtils.emptyIfNull(proj.getIncludeFields());
			List<StructuredElement> projectionExcludeFields = ListUtils.emptyIfNull(proj.getExcludeFields());
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			for (StructuredElement element : projectionIncludeFields) {
				if (buffer.length() > 1) {
					buffer.append(",");
				}
				String includeField = fieldQueryString(game, element.getType(), element.getConceptName(),
						element.getPeriodName(), element.getInstanceDate(), element.getField());
				buffer.append(String.format("\"%s\":1", includeField));

				// append type field when necessary to permit correct creation
				// of
				// instance of class concept
				String typeField = appendTypeField(includeField);
				if (typeField != null) {
					buffer.append(String.format(",\"%s\":1", typeField));
				}
			}

			for (StructuredElement element : projectionExcludeFields) {
				if (buffer.length() > 1) {
					buffer.append(",");
				}
				buffer.append(
						String.format("\"%s\":0", fieldQueryString(game, element.getType(), element.getConceptName(),
								element.getPeriodName(), element.getInstanceDate(), element.getField())));
			}
			buffer.append("}");
			result = buffer.toString();
		}
		return result;
	}

	private String appendTypeField(String field) {
		if (field != null && field.contains(".obj.")) {
			return field.replaceFirst(".obj.*", ".type");
		}

		return null;

	}

	private Query addSort(Query query, List<SortItem> sortItems) {
		if (sortItems != null) {
//			query.with(new Sort(createDataOders(sortItems)));
			query.with(Sort.by(createDataOders(sortItems)));
		}
		return query;
	}

	private Query addStructuredSort(Game game, Query query, List<StructuredSortItem> sortItems) {
		if (sortItems != null) {
//			query.with(new Sort(createStructuredDataOders(game, sortItems)));
			query.with(Sort.by(createStructuredDataOders(game, sortItems)));
		}
		return query;
	}

	private String queryPartToString(Game game, String concept, List<QueryElement> parts) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		if (parts != null) {
			for (int i = 0; i < parts.size(); i++) {
				QueryElement part = parts.get(i);
				buffer.append("\"" + fieldQueryString(game, concept, part) + "\"");
				buffer.append(":").append(part.getClause().trim());
				if (i < parts.size() - 1) {
					buffer.append(",");
				}
			}
		}
		buffer.append("}");

		return buffer.toString();
	}

	private String fieldQueryString(Game game, String fieldType, String conceptName, String periodName,
			Date instanceDate, String field) {
		String queryString = null;
		if ("customData".equals(fieldType)) {
			queryString = "customData";
			if (field != null)
				queryString += "." + field.trim();
		} else if ("pointConcept".equals(fieldType)) {
			queryString = String.format("concepts.%s", convertConceptToString(fieldType));
			if (conceptName != null) {
				queryString += "." + conceptName;
			}
			if (field != null) {
				queryString += String.format(".obj.%s", field.trim());
			}
		} else if ("badgeCollectionConcept".equals(fieldType)) {
			queryString = String.format("concepts.%s", convertConceptToString(fieldType));
			if (conceptName != null) {
				queryString += "." + conceptName;
			}
			if (field != null) {
				queryString += String.format(".obj.%s", field.trim());
			}
		} else if ("periodicPointConcept".equals(fieldType)) {
			queryString = String.format("concepts.%s", convertConceptToString(fieldType));
			if (conceptName != null) {
				queryString += "." + conceptName;
			}
			if (periodName != null) {
				queryString += ".obj.periods." + periodName;
			}
			if (instanceDate != null) {
				PeriodInstance instance = ClassificationUtils.retrieveWindow(game, periodName, conceptName,
						instanceDate.getTime(), -1);
				if (instance != null) {
					queryString += ".instances." + new DateTime(instance.getStart()).toString("YYYY-MM-dd'T'HH:mm:ss");
				}
			}
			if (field != null) {
				queryString += "." + field.trim();
			}
		} else if ("challengeConcept".equals(fieldType)) {
			// if field is field or challenge-metadata
			queryString = "concepts." + convertConceptToString(fieldType);
			if (conceptName != null) {
				queryString += "." + conceptName;
			}
			if (isMetaField(field)) {
				queryString += ".obj." + field;
			} else {
				queryString += ".obj.fields." + field;
			}
		} else if ("general".equals(fieldType)) {
			queryString = field;
		}

		return queryString;
	}

	private String fieldQueryString(Game game, String concept, QueryElement queryPart) {
		String queryString = null;
		if ("customData".equals(concept)) {
			queryString = fieldQueryString(game, concept, null, null, null, queryPart.getConceptName());
		} else if ("pointConcept".equals(concept)) {
			queryString = fieldQueryString(game, concept, queryPart.getConceptName(), null, null, "score");
		} else if ("badgeCollectionConcept".equals(concept)) {
			queryString = fieldQueryString(game, concept, queryPart.getConceptName(), null, null, "badgeEarned");
		} else if ("periodicPointConcept".equals(concept)) {
			queryString = fieldQueryString(game, concept, queryPart.getConceptName(), queryPart.getPeriodName(),
					queryPart.getInstanceDate(), "score");
		} else if ("challengeConcept".equals(concept)) {
			queryString = fieldQueryString(game, concept, queryPart.getConceptName(), null, null, queryPart.getField());
		}

		return queryString;
	}

	private boolean isMetaField(String field) {
		Field[] classVars = ChallengeConcept.class.getDeclaredFields();
		boolean isMetaField = false;
		for (Field classVar : classVars) {
			if (isMetaField = field.equals(classVar.getName())) {
				break;
			}
		}

		return isMetaField;
	}

	private String convertConceptToString(String concept) {
		String result = null;
		switch (concept) {
		case "pointConcept":
			result = "PointConcept";
			break;
		case "badgeCollectionConcept":
			result = "BadgeCollectionConcept";
			break;
		case "periodicPointConcept":
			result = "PointConcept";
			break;
		case "challengeConcept":
			result = "ChallengeConcept";
			break;
		case "customData":
			result = "customData";
			break;
		default:
			break;
		}

		return result;
	}

	private Order[] createDataOders(List<SortItem> sortItems) {
		Order[] orders = new Order[sortItems.size()];
		int index = 0;
		for (SortItem sortItem : sortItems) {
			orders[index++] = new Order(Direction.fromString(sortItem.getDirection().name()), sortItem.getField());
		}
		return orders;
	}

	private Order[] createStructuredDataOders(Game game, List<StructuredSortItem> sortItems) {
		Order[] orders = new Order[sortItems.size()];
		int index = 0;
		for (StructuredSortItem sortItem : sortItems) {
			orders[index++] = new Order(Direction.fromString(sortItem.getDirection().name()),
					fieldQueryString(game, sortItem.getField().getType(), sortItem.getField().getConceptName(),
							sortItem.getField().getPeriodName(), sortItem.getField().getInstanceDate(),
							sortItem.getField().getField()));
		}
		return orders;
	}

	private void exceptionHandler(String gameId, Exception e) {
		LogHub.error(gameId, logger, "Exception running mongo query in search", e);
		throw new IllegalArgumentException("Query seems to be not valid");
	}

	@Override
	public StatePersistence search(String gameId, String playerId, List<String> points, List<String> badges) {
		StatePersistence state = null;
		Criteria criteria = Criteria.where("gameId").is(gameId).and("playerId").is(playerId);
		Query query = new Query();
		query.addCriteria(criteria);
		query.fields().include("id").include("gameId").include("playerId").include("customData").include("inventory").include("levels").include("metaData");
		
		if (points != null && !points.isEmpty()) {
			for (String pt: points) {
				query.fields().include("concepts.PointConcept." + pt );				
			}	
		} else {
			query.fields().include("concepts.PointConcept");
		}
		
		if (badges != null && !badges.isEmpty()) {
			for (String bg: badges) {
				query.fields().include("concepts.BadgeCollectionConcept." + bg );				
			}
		} else {
			query.fields().include("concepts.BadgeCollectionConcept");
		}
		
		List<StatePersistence> result = mongo.find(query, StatePersistence.class);

		if (!result.isEmpty()) {
			state = result.get(0);
		}

		return state;
	}

}
