package cern.accsoft.steering.jmad.domain.machine;

import java.util.ArrayList;
import java.util.List;

import cern.accsoft.steering.jmad.domain.beam.Beam;

/**
 * Represents a <a href="http://mad.web.cern.ch/mad/Introduction/sequence.html">SEQUENCE</a> in the MadX-model. This
 * class also manages the its ranges and knows about its own {@link SequenceDefinition}.
 * 
 * @author kfuchsbe
 */
public class SequenceImpl implements Sequence {

    /** The definition from which this sequence is derived */
    private final SequenceDefinition sequenceDefinition;

    /** the available ranges of the sequence */
    private final List<Range> ranges = new ArrayList<Range>();

    /**
     * simple constructor, just give the sequenceDefinition.
     * 
     * @param sequenceDefinition the {@link SequenceDefinition} to use to create this sequence
     */
    public SequenceImpl(SequenceDefinition sequenceDefinition) {
        this.sequenceDefinition = sequenceDefinition;
        for (RangeDefinition rangeDefinition : this.sequenceDefinition.getRangeDefinitions()) {
            this.ranges.add(new Range(rangeDefinition));
        }
    }

    @Override
    public String getName() {
        return getSequenceDefinition().getName();
    }

    @Override
    public List<Range> getRanges() {
        return ranges;
    }

    @Override
    public Range getDefaultRange() {
        /*
         * we just return the first entry, if available.
         */
        if (ranges.isEmpty()) {
            return null;
        } else {
            return ranges.get(0);
        }
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public final Beam getBeam() {
        return getSequenceDefinition().getBeam();
    }

    public SequenceDefinition getSequenceDefinition() {
        return sequenceDefinition;
    }
}
