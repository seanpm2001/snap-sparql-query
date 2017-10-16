package org.semanticweb.owlapi.sparql.builtin.eval;

import org.semanticweb.owlapi.sparql.api.*;
import org.semanticweb.owlapi.sparql.builtin.DateTime;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Matthew Horridge Stanford Center for Biomedical Informatics Research 05/06/15
 */
public class NOW_Evaluator implements BuiltInCallEvaluator {

    @Override
    public EvaluationResult evaluate(List<Expression> args, SolutionMapping sm) {
        // NOW() must return the same value in a given query execution
        DateTime ts = new DateTime(ZonedDateTime.now());
        return EvaluationResult.getResult(new Literal(Datatype.getXSDDateTime(), ts.getFormattedDateTime(), ""));
    }
}
