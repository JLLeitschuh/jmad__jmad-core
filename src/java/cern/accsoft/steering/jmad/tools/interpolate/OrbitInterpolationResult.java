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

package cern.accsoft.steering.jmad.tools.interpolate;

import cern.accsoft.steering.jmad.domain.result.tfs.TfsResult;

/**
 * This interface represents the result of an orbit interpolation.
 * 
 * @author muellerg
 */
public interface OrbitInterpolationResult {

    /**
     * @return the resulting interpolated orbit as a {@link TfsResult}.
     */
    public abstract TfsResult getTfsResult();
}
