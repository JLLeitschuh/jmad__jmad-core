// @formatter:off
 /*******************************************************************************
 *
 * This file is part of JMad.
 * 
 * Copyright (c) 2008-2011, Kajetan Fuchsberger. All rights reserved.
 * 
 * JMad is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * JMad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with JMad.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ******************************************************************************/
// @formatter:on
package cern.accsoft.steering.jmad.domain.result.tfs;

import java.util.Collection;

import cern.accsoft.steering.jmad.domain.var.GlobalVariable;
import cern.accsoft.steering.jmad.util.MadxVarType;

/**
 * An implementation of this interface shall contain all the information which is available in the Twiss-summary of
 * madx. i.e. in the header of a tfs-file.
 * <p>
 * In general the values in here are a representation of the values of the <a
 * href="http://mad.web.cern.ch/mad/Introduction/tables.html#summ">summary table</a> of MadX.
 * 
 * @author kfuchsbe
 */
public interface TfsSummary {

    /**
     * Retrieve one of the Twiss Summary Values...
     * <p>
     * <b>NOTE:</b> Avoid this access. Better use {@link #getDoubleValue(GlobalVariable)}.
     * 
     * @param key the name of the summary value to get
     * @return the value of this global parameter
     * @see #getDoubleValue(GlobalVariable)
     */
    public abstract Double getDoubleValue(String key);

    /**
     * retrieve a double value for the given global variable.
     * <p>
     * This is the preferred way to access the data for double values!
     * 
     * @param variable the variable for which to get the value
     * @return the value
     */
    public abstract Double getDoubleValue(GlobalVariable variable);

    /**
     * Retrieves the String representation of the data for the given key.
     * <p>
     * NOTE: avoid this kind of data access! Use {@link #getStringValue(GlobalVariable)} instead whenever possible!
     * 
     * @param key the key for which to get the data
     * @return the data as String
     * @see #getStringValue(GlobalVariable)
     */
    public abstract String getStringValue(String key);

    /**
     * Retrieves the String representation of the data for the given variable.
     * <p>
     * This is the preferred way to access String data in the summary.
     * 
     * @param variable the variable for which to get the string representation of the value.
     * @return the String representation of the data
     */
    public abstract String getStringValue(GlobalVariable variable);

    /**
     * @return all the keys for which there exist values in this summary
     */
    public Collection<String> getKeys();

    /**
     * the type of the value for the given key
     * 
     * @param key the key for which to query the type
     * @return the type for the key
     */
    public MadxVarType getVarType(String key);

    /**
     * the type for the variable in this summary.
     * 
     * @param variable the variable for which to get the type
     * @return the type for the variable
     */
    public MadxVarType getVarType(GlobalVariable variable);
}
