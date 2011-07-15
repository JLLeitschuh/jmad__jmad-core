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

/*
 * $Id: Sextupole.java,v 1.1 2009-01-15 11:46:27 kfuchsbe Exp $
 * 
 * $Date: 2009-01-15 11:46:27 $ $Revision: 1.1 $ $Author: kfuchsbe $
 * 
 * Copyright CERN, All Rights Reserved.
 */
package cern.accsoft.steering.jmad.domain.elem.impl;

import cern.accsoft.steering.jmad.domain.elem.MadxElementType;

/**
 * Represents in JMad an element of type <a href="http://mad.web.cern.ch/mad/Introduction/sextupole.html">SEXTUPOLE</a>
 * in MadX.
 * 
 * @author Kajetan Fuchsberger (kajetan.fuchsberger at cern.ch)
 */
public class Sextupole extends AbstractElement {

    /** the normal sextupole coefficent. */
    public static final String ATTR_K2 = "k2";

    /** the skew sextupole coefficient. */
    public static final String ATTR_K2S = "k2s";

    /** the tilt of the sextupole. */
    public static final String ATTR_TILT = "tilt";

    /**
     * the constructor, which needs the madxElementType and the name of the element
     * 
     * @param madxElementType the type which represents this specific element in MadX
     * @param name the name of the Sextupole
     */
    public Sextupole(MadxElementType madxElementType, String name) {
        super(madxElementType, name);

        addAttribute(ATTR_K2);
        addAttribute(ATTR_K2S);
        addAttribute(ATTR_TILT);
    }

    public double getK2() {
        return getAttribute(ATTR_K2);
    }

    /* short variable name (k2) to use the same as madx */
    public void setK2(double k2) { // NOPMD by kaifox on 6/25/10 5:36 PM
        setAttribute(ATTR_K2, k2);
    }

    public double getK2s() {
        return getAttribute(ATTR_K2S);
    }

    public void setK2s(double k2s) {
        setAttribute(ATTR_K2S, k2s);
    }

    public void setTilt(double tilt) {
        setAttribute(ATTR_TILT, tilt);
    }

    public double getTilt() {
        return getAttribute(ATTR_TILT);
    }
}
