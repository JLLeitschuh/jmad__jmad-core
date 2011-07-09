package cern.accsoft.steering.jmad.task;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cern.accsoft.steering.jmad.domain.elem.Element;
import cern.accsoft.steering.jmad.domain.elem.MadxElementType;
import cern.accsoft.steering.jmad.domain.elem.impl.BeamBeam;
import cern.accsoft.steering.jmad.domain.elem.impl.Quadrupole;
import cern.accsoft.steering.jmad.kernel.task.DefineAndInstallElements;

public class DefineAndInstallElementTest {

	@Test
	public void installElementTest() {
		Quadrupole quad = new Quadrupole(MadxElementType.QUADRUPOLE,"testQuad");
		quad.setPosition(1000);
		quad.setAttribute("k1", 1E-4);
		quad.setAttributesInitialized(true);
		
		BeamBeam beamBeam = new BeamBeam(MadxElementType.BEAMBEAM,"testBeamBeam");
		beamBeam.setAttribute("charge", 0.0);
		beamBeam.setAttributesInitialized(true);
		
		List<Element> input = new ArrayList<Element>();
		input.add(quad);
		input.add(beamBeam);
		
		String sequ = "lchb1";
		
		DefineAndInstallElements testTask = new DefineAndInstallElements(sequ,input);
		assertEquals(testTask.compose(),"\n"+
				"// ***** BEGIN autogenerated task: cern.accsoft.steering.jmad.kernel.task.DefineAndInstallElements *****\n" +
				"testQuad: quadrupole, k1=1.0E-4;\n" +
				"testBeamBeam: beambeam, charge=0.0;\n" +
				"seqedit, sequence=lchb1;\n" +
				"install, element=testQuad, class=quadrupole, at=1000.0;\n" +
				"install, element=testBeamBeam, class=beambeam, at=0.0;\n" +
				"flatten;\n" +
				"endedit;\n" +
				"// ***** END autogenerated task: cern.accsoft.steering.jmad.kernel.task.DefineAndInstallElements *****");
	}
}
