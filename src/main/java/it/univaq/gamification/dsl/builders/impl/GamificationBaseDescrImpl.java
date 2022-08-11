// package it.univaq.gamification.dsl.builders.impl;
//
// import it.univaq.gamification.dsl.builders.GamificationElement;
// import it.univaq.gamification.dsl.utils.ConstraintHelper;
// import org.drools.compiler.lang.api.DescrBuilder;
// import org.drools.compiler.lang.api.impl.BaseDescrBuilderImpl;
// import org.drools.compiler.lang.descr.BaseDescr;
// import org.drools.compiler.lang.descr.PatternDescr;
//
// public class GamificationBaseDescrImpl<P extends DescrBuilder<?, ? extends BaseDescr>>
//         extends BaseDescrBuilderImpl<P, PatternDescr>
//         implements GamificationElement<P> {
//
//     protected GamificationBaseDescrImpl(P parent, PatternDescr descr) {
//         super(parent, descr);
//     }
//
//     @Override
//     public P declare(String bindName, String value) {
//         ConstraintHelper.addBindConstraint(this.descr, value, bindName);
//         return this.parent;
//     }
//
//     @Override
//     public P constraint(String constraint) {
//         ConstraintHelper.addConstraint(this.descr, constraint);
//         return this.parent;
//     }
// }
