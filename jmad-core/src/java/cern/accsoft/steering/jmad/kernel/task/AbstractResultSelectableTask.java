package cern.accsoft.steering.jmad.kernel.task;

import java.util.ArrayList;
import java.util.List;

import cern.accsoft.steering.jmad.domain.result.ResultType;
import cern.accsoft.steering.jmad.domain.result.tfs.TfsResultRequest;
import cern.accsoft.steering.jmad.domain.var.MadxVariable;
import cern.accsoft.steering.jmad.kernel.cmd.Command;
import cern.accsoft.steering.jmad.kernel.cmd.SelectCommand;

public abstract class AbstractResultSelectableTask extends AbstractTask {

    private final TfsResultRequest resultRequest;

    public AbstractResultSelectableTask(TfsResultRequest resultRequest) {
        super();
        this.resultRequest = resultRequest;
    }

    protected List<Command> createSelectCommands(String selectFlag) {
        List<Command> commands = new ArrayList<Command>();

        if (resultRequest != null) {
            SelectCommand select = new SelectCommand();
            select.setFlag(selectFlag);
            select.setClear(true);
            commands.add(select);

            /*
             * add the select commands for the patterns
             */
            for (String pattern : resultRequest.getElementPattern()) {
                select = createColumnsSelectCommand(selectFlag);
                select.setPattern(pattern);
                commands.add(select);
            }

            /*
             * add ElementClasses that are included inside the Request
             */
            for (String elemClass : resultRequest.getElementClasses()) {
                select = createColumnsSelectCommand(selectFlag);
                select.setElementClass(elemClass);
                commands.add(select);
            }
        }

        return commands;
    }

    /**
     * creates a select command for the given flas, which contains all the columns defined by the variables
     * 
     * @param selectFlag the select-flag for which to compose the command
     * @return the configured select-command
     */
    private SelectCommand createColumnsSelectCommand(String selectFlag) {
        SelectCommand select = new SelectCommand();
        select.setFlag(selectFlag);
        // add requested variables
        StringBuffer columns = new StringBuffer("");
        for (MadxVariable var : resultRequest.getResultVariables()) {
            if (columns.length() > 0) {
                columns.append(',');
            }
            columns.append(var.getMadxName());
        }
        if (columns.length() > 0) {
            select.setColumn(columns.toString());
        }
        return select;
    }

    @Override
    public ResultType getResultType() {
        if ((resultRequest == null) || (getOutputFile() == null)) {
            return super.getResultType();
        } else {
            return ResultType.TFS_RESULT;
        }
    }

}