package qmpro;

import qmpro.SearchbyAgent;
import qmpro.SearchbyCallDuration;
import qmpro.SearchbyObserver;
import qmpro.SearchbyRecordingID;
import qmpro.SearchbyScore;
import qmpro.SearchbyScoreAboveBelow;

import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import org.junit.jupiter.api.TestMethodOrder;

import java.text.ParseException;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.JUnitCore;

@TestMethodOrder(OrderAnnotation.class)
public class AllTests extends common {

	public AllTests() throws ParseException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Test
	@Order(1)
	// @RepeatedTest(value = 1)
	public void runAllTests2() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class, SearchbyObserver.class };

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}

	@Test
	@Order(2)
	// @RepeatedTest(value = 10)
	public void runAllTests1() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchbyRecordingID.class, SearchbyScore.class, SearchbyScoreAboveBelow.class };
		// ParallelComputer(true,true) will run all classes and methods
		// in parallel. (First arg for classes, second arg for methods)

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}

	@Test
	@Order(3)
	// @RepeatedTest(value = 1)
	public void runAllTests3() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchbyTeam.class, SearchbyCampaign.class, SearchAfter.class };

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}

	
	@Test
	@Order(4)
	// @RepeatedTest(value = 1)
	public void runAllTests4() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchBefore.class, SearchLast24hours.class, SearchLast365days.class };

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}

	

	@Test
	@Order(5)
	// @RepeatedTest(value = 1)
	public void runAllTests5() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { Searchlast7days.class, SearchLastMonth.class, SearchLastWeek.class };

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}



	@Test
	@Order(6)
	// @RepeatedTest(value = 1)
	public void runAllTests6() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchthisWeek.class, SearchToday.class, SearchLastWeekfromMonday.class };

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}


	@Test
	@Order(7)
	// @RepeatedTest(value = 1)
	public void runAllTests7() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchthisWeekfromMonday.class, SearchbySpecificDates.class, SearchbyCoached.class  };

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}


	@Test
	@Order(8)
	// @RepeatedTest(value = 1)
	public void runAllTests8() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchbyEvaluatedCalls.class, SearchbyApproved.class, SearchbyDisapproved.class};

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}
	

	@Test
	@Order(9)
	// @RepeatedTest(value = 1)
	public void runAllTests9() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchbyAutofailurebyForm.class, SearchbyEvaluationsbyanotherUser.class, SearchbyRecordingsPublic.class};

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}
	
	

	@Test
	@Order(10)
	// @RepeatedTest(value = 1)
	public void runAllTests10() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchbySupervisor.class, SearchCalibrations.class, SearchEvaluations.class};

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}
	

	@Test
	@Order(11)
	// @RepeatedTest(value = 1)
	public void runAllTests11() {
		// Class<?>[] classes = { SearchbyAgent.class, SearchbyCallDuration.class,
		// SearchbyObserver.class , SearchbyRecordingID.class, SearchbyScore.class};
		Class<?>[] classes = { SearchEvaluationswithattachments.class, MakeCommentsOnEvaluationsNQueryforIt.class, SupervisorPublicQuery.class};

		JUnitCore.runClasses(new ParallelComputer(true, true), classes);

	}
	
	
	@AfterClass
	public static void Publish_report() {
		extent.flush();
	}

}
