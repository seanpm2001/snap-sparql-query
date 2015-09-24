package org.semanticweb.owlapi.sparql.api;

import com.google.common.base.Optional;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;

import java.util.Collection;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 26/07/2012
 */
public class DataPropertyVariable extends AbstractVariable implements AtomicDataProperty{

    public DataPropertyVariable(String variableName) {
        super(variableName);
    }

    public <R, E extends Throwable> R accept(Visitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    @Override
    public RDFTerm getBound(IRI iri) {
        return new AtomicIRI(iri);
    }

    @Override
    public void collectVariables(Collection<Variable> variables) {
        variables.add(this);
    }

    @Override
    public <R, E extends Throwable, C> R accept(ExpressionVisitor<R, E, C> visitor, C context) throws E {
        return visitor.visit(this, context);
    }

    @Override
    public Optional<? extends AtomicDataProperty> bind(SolutionMapping sm) {
        Optional<AtomicIRI> term = sm.getIRIForVariable(this);
        if(term.isPresent()) {
            return Optional.of(new DataProperty(term.get().getIRI()));
        }
        else {
            return Optional.absent();
        }
    }

    @Override
    public OWLDataPropertyExpression toOWLObject(OWLDataFactory df) {
        throw new UnboundVariableTranslationException();
    }
}
