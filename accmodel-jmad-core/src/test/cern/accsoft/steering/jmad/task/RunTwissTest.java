package cern.accsoft.steering.jmad.task;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import cern.accsoft.steering.jmad.domain.result.tfs.TfsResultRequest;
import cern.accsoft.steering.jmad.domain.result.tfs.TfsResultRequestImpl;
import cern.accsoft.steering.jmad.domain.twiss.TwissInitialConditionsImpl;
import cern.accsoft.steering.jmad.domain.var.enums.MadxTwissVariable;
import cern.accsoft.steering.jmad.kernel.task.AbstractTask;
import cern.accsoft.steering.jmad.kernel.task.RunTwiss;

public class RunTwissTest {
    private static TwissInitialConditionsImpl TWISS = new TwissInitialConditionsImpl("test-twiss");
    private AbstractTask task = null;

    @BeforeClass
    public static void initTwiss() throws Exception {
        TWISS.setDx(0.0);
        TWISS.setDy(1.1);
    }

    @Test
    public void testNoResult() {
        task = new RunTwiss(TWISS);
        assertEquals("\n// ***** BEGIN autogenerated task: cern.accsoft.steering.jmad.kernel.task.RunTwiss *****\n"
                + "delete, table=twiss;\n" 
                + "delete, table=summ;\n" 
                + "twiss, dx=0.0, dy=1.1, chrom;\n"
                + "// ***** END autogenerated task: cern.accsoft.steering.jmad.kernel.task.RunTwiss *****", task
                .compose());
    }

    @Test
    public void testSummaryOnly() {
        TfsResultRequest request = TfsResultRequestImpl.createSummaryOnlyRequest();
        task = new RunTwiss(TWISS, request);
        System.out.print(task.compose());
        assertEquals("\n// ***** BEGIN autogenerated task: cern.accsoft.steering.jmad.kernel.task.RunTwiss *****\n"
                + "delete, table=twiss;\n" 
                + "delete, table=summ;\n"
                + "select, flag=twiss, clear;\n"
                + "select, flag=twiss, class=jmad_unknown, column=jmad_unknown;\n"
                + "twiss, dx=0.0, dy=1.1, chrom;\n"
                + "// ***** END autogenerated task: cern.accsoft.steering.jmad.kernel.task.RunTwiss *****", task
                .compose());

    }

    @Test
    public void testSimpleResult() {
        TfsResultRequestImpl request = new TfsResultRequestImpl();
        request.addElementFilter("BPMI.*");
        request.addVariables(Arrays.asList(new MadxTwissVariable[] { MadxTwissVariable.NAME, MadxTwissVariable.X,
                MadxTwissVariable.Y, MadxTwissVariable.KEYWORD }));
        task = new RunTwiss(TWISS, request);
        assertEquals("\n// ***** BEGIN autogenerated task: cern.accsoft.steering.jmad.kernel.task.RunTwiss *****\n"
                + "delete, table=twiss;\n" 
                + "delete, table=summ;\n"
                + "select, flag=twiss, clear;\n" 
                + "select, flag=twiss, pattern=\"BPMI.*\", column=name,x,y,keyword;\n"
                + "twiss, dx=0.0, dy=1.1, chrom;\n"
                + "// ***** END autogenerated task: cern.accsoft.steering.jmad.kernel.task.RunTwiss *****", task
                .compose());
    }
}