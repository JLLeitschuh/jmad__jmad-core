// @formatter:off
 /*******************************************************************************
 *
 * This file is part of JMad.
 * 
 * Copyright (c) 2008-2011, CERN. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 ******************************************************************************/
// @formatter:on

package cern.accsoft.steering.jmad.kernel.cmd;

import java.util.ArrayList;
import java.util.List;

import cern.accsoft.steering.jmad.domain.beam.Beam;
import cern.accsoft.steering.jmad.kernel.cmd.param.GenericParameter;
import cern.accsoft.steering.jmad.kernel.cmd.param.Parameter;

/**
 * Represents the command in MadX which resets the beam to the default values. (<a
 * href="http://mad.web.cern.ch/mad/Introduction/resbeam.html">RESBEAM</a>).
 * 
 * @author Kajetan Fuchsberger (kajetan.fuchsberger at cern.ch)
 */
public class ResbeamCommand extends AbstractCommand {

    /** The name of this command */
    private static final String CMD_NAME = "resbeam";

    /** The name of the sequence whose beam to reset */
    private final String sequence;

    /**
     * creates a resbeam command, probably associated with a special sequence (defined in beam);
     * 
     * @param beam the beam which to reset
     */
    public ResbeamCommand(Beam beam) {
        sequence = beam.getSequence();
    }

    @Override
    public String getName() {
        return CMD_NAME;
    }

    @Override
    public List<Parameter> getParameters() {
        ArrayList<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new GenericParameter<String>("sequence", sequence));
        return parameters;
    }

    public String getSequence() {
        return sequence;
    }

}