package edu.wustl.cse231s.rice.classic;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import edu.rice.hj.Module0;
import edu.rice.hj.Module1;
import edu.rice.hj.Module2;
import edu.rice.hj.ModuleEventLogging;
import edu.rice.hj.api.HjFuture;
import edu.rice.hj.api.HjMetrics;
import edu.rice.hj.api.HjPhaser;
import edu.rice.hj.api.HjPhaserMode;
import edu.rice.hj.api.HjPhaserPair;
import edu.rice.hj.api.HjPoint;
import edu.rice.hj.api.HjProcedure;
import edu.rice.hj.api.HjProcedureInt1D;
import edu.rice.hj.api.HjProcedureInt2D;
import edu.rice.hj.api.HjRegion.HjRegion1D;
import edu.rice.hj.api.HjRegion.HjRegion2D;
import edu.rice.hj.api.HjRunnable;
import edu.rice.hj.api.HjSuspendable;
import edu.rice.hj.api.HjSuspendingCallable;
import edu.rice.hj.api.HjSuspendingProcedure;
import edu.rice.hj.api.HjSuspendingProcedureInt1D;
import edu.rice.hj.api.HjSuspendingProcedureInt2D;
import edu.rice.hj.api.SuspendableException;
import edu.rice.hj.runtime.config.HjConfiguration;
import edu.rice.hj.runtime.config.HjSystemProperty;
import edu.rice.hj.runtime.metrics.AbstractMetricsManager;
import edu.rice.hj.runtime.region.RectangularRegion2D;
import edu.wustl.cse231s.rice.classic.contrib.api.ContentionLevel;
import edu.wustl.cse231s.rice.classic.contrib.api.DoubleAccumulationDeterminismPolicy;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;
import edu.wustl.cse231s.rice.classic.contrib.api.Reducer;
import edu.wustl.cse231s.rice.classic.contrib.runtime.AccumulatorRuntime;
import edu.wustl.cse231s.rice.classic.options.AwaitFuturesOption;
import edu.wustl.cse231s.rice.classic.options.ChunkedOption;
import edu.wustl.cse231s.rice.classic.options.ObjectBasedIsolationOption;
import edu.wustl.cse231s.rice.classic.options.PhasedEmptyOption;
import edu.wustl.cse231s.rice.classic.options.PhasedOption;
import edu.wustl.cse231s.rice.classic.options.RegisterAccumulatorsOption;
import edu.wustl.cse231s.rice.classic.options.SingleOption;
import edu.wustl.cse231s.rice.classic.options.SystemPropertiesOption;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum HabaneroClassic {
	;
	public static AwaitFuturesOption await(HjFuture<?> futureA, HjFuture<?>... futuresBtoZ) {
		return new AwaitFuturesOption(futureA, futuresBtoZ);
	}

	public static ChunkedOption chunked() {
		return new ChunkedOption();
	}

	public static ChunkedOption chunked(int size) {
		return new ChunkedOption(size);
	}

	public static RegisterAccumulatorsOption register(FinishAccumulator<?> accumulatorA,
			FinishAccumulator<?>... accumulatorBtoZ) {
		return new RegisterAccumulatorsOption(accumulatorA, accumulatorBtoZ);
	}

	public static ObjectBasedIsolationOption objectBased(Object participant) {
		return new ObjectBasedIsolationOption(participant);
	}

	public static ObjectBasedIsolationOption objectBasedAll(Object participantA, Object participantB,
			Object... participantsCtoZ) {
		return new ObjectBasedIsolationOption(participantA, participantB, participantsCtoZ);
	}

	public static PhasedOption phased(HjPhaserPair phaserPairA, HjPhaserPair... phaserPairsBtoZ) {
		return new PhasedOption(phaserPairA, phaserPairsBtoZ);
	}

	public static PhasedEmptyOption phased() {
		return new PhasedEmptyOption();
	}

	public static SingleOption single(Runnable runnable) {
		return new SingleOption(runnable);
	}

	public static int numThreads() {
		return Module1.numWorkerThreads();
	}

	public static HjPhaser newPhaser(HjPhaserMode phaserMode) {
		return Module0.newPhaser(phaserMode);
	}

	public static void launchHabaneroApp(HjSuspendable body) {
		Module1.launchHabaneroApp(body);
	}

	private static AtomicInteger preFinalizeCallbackEnterVsLeaveCount = new AtomicInteger(0);

	public static boolean isLaunched() {
		boolean result = true;
		try {
			HjConfiguration.runtime();
		} catch (IllegalStateException ise) {
			result = false;
		}
		return result;
	}

	public static void launchHabaneroApp(HjSuspendable body, Runnable preFinalizeCallback) {
		Module1.launchHabaneroApp(body, () -> {
			preFinalizeCallbackEnterVsLeaveCount.incrementAndGet();
			try {
				preFinalizeCallback.run();
			} finally {
				preFinalizeCallbackEnterVsLeaveCount.decrementAndGet();
			}
		});
	}

	public static void launchHabaneroApp(SystemPropertiesOption systemPropertiesOption, HjSuspendable body) {
		File eventLogFile = systemPropertiesOption.getEventLogFile();
		if (eventLogFile != null) {
			HjSystemProperty.eventLogging.set(true);
		}
		if (systemPropertiesOption.isDumpStatisticsDesired()) {
			HjSystemProperty.abstractMetrics.set(true);
		}
		launchHabaneroApp(body, () -> {
			if (systemPropertiesOption.isDumpStatisticsDesired()) {
				dumpStatistics();
			}
			if( eventLogFile != null ) {
				ModuleEventLogging.dumpEventLog(eventLogFile.getAbsolutePath());
			}
		});
	}

	public static void finish(HjSuspendable body) throws SuspendableException {
		Module1.finish(body);
	}

	public static void async(HjSuspendable body) {
		Module1.async(body);
	}

	public static void finish(RegisterAccumulatorsOption registerAccumulatorsOption, HjSuspendable body)
			throws SuspendableException {
		AccumulatorRuntime.finish(registerAccumulatorsOption.getAccumulators(), body);
	}

	public static FinishAccumulator<Integer> newIntegerFinishAccumulator(NumberReductionOperator operator,
			ContentionLevel contentionLevel) {
		return AccumulatorRuntime.newIntegerFinishAccumulator(operator, contentionLevel);
	}

	public static FinishAccumulator<Integer> newIntegerFinishAccumulator(NumberReductionOperator operator) {
		return newIntegerFinishAccumulator(operator, ContentionLevel.HIGH);
	}

	public static FinishAccumulator<Double> newDoubleFinishAccumulator(NumberReductionOperator operator,
			ContentionLevel contentionLevel, DoubleAccumulationDeterminismPolicy determinismPolicy) {
		return AccumulatorRuntime.newDoubleFinishAccumulator(operator, contentionLevel, determinismPolicy);
	}

	public static FinishAccumulator<Double> newDoubleFinishAccumulator(NumberReductionOperator operator,
			ContentionLevel contentionLevel) {
		return newDoubleFinishAccumulator(operator, contentionLevel, DoubleAccumulationDeterminismPolicy.DETERMINISTIC);
	}

	public static FinishAccumulator<Double> newDoubleFinishAccumulator(NumberReductionOperator operator) {
		return newDoubleFinishAccumulator(operator, ContentionLevel.HIGH);
	}

	public static <T> FinishAccumulator<T> newReducerFinishAccumulator(Reducer<T> reducer,
			ContentionLevel contentionLevel) {
		return AccumulatorRuntime.newReducerFinishAccumulator(reducer, contentionLevel);
	}

	public static <T> FinishAccumulator<T> newReducerFinishAccumulator(Reducer<T> reducer) {
		return newReducerFinishAccumulator(reducer, ContentionLevel.HIGH);
	}

	public static <R> HjFuture<R> future(HjSuspendingCallable<R> body) {
		return Module1.future(body);
	}

	public static void async(AwaitFuturesOption awaitOption, HjSuspendable body) {
		Module1.asyncAwait(awaitOption.getFutures(), body);
	}

	public static <R> HjFuture<R> future(AwaitFuturesOption awaitOption, HjSuspendingCallable<R> body) {
		return Module1.futureAwait(awaitOption.getFutures(), body);
	}

	public static void forseq(int start, int endInclusive, HjProcedureInt1D body) {
		Module1.forseqNb(start, endInclusive, body);
	}

	public static void forasync(int start, int endInclusive, HjProcedureInt1D body) {
		Module1.forasyncNb(start, endInclusive, body);
	}

	public static void forasync(ChunkedOption chunkedOption, int start, int endInclusive, HjProcedureInt1D body) {
		if (chunkedOption.isSizeDecidedBySystem()) {
			Module1.forasyncNbChunked(start, endInclusive, body);
		} else {
			Module1.forasyncNbChunked(start, endInclusive, chunkedOption.getSize(), body);
		}
	}

	public static void forall(int start, int endInclusive, HjSuspendingProcedureInt1D body)
			throws SuspendableException {
		Module1.forall(start, endInclusive, body);
	}

	public static void forall(ChunkedOption chunkedOption, int start, int endInclusive, HjSuspendingProcedureInt1D body)
			throws SuspendableException {
		if (chunkedOption.isSizeDecidedBySystem()) {
			Module1.forallChunked(start, endInclusive, body);
		} else {
			Module1.forallChunked(start, endInclusive, chunkedOption.getSize(), body);
		}
	}

	public static void forseq2d(int startA, int endAInclusive, int startB, int endBInclusive, HjProcedureInt2D body) {
		Module1.forseqNb(startA, endAInclusive, startB, endBInclusive, body);
	}

	public static void forasync2d(int startA, int endAInclusive, int startB, int endBInclusive, HjProcedureInt2D body) {
		Module1.forasyncNb(startA, endAInclusive, startB, endBInclusive, body);
	}

	public static void forasync2d(ChunkedOption chunkedOption, int startA, int endAInclusive, int startB,
			int endBInclusive, HjProcedureInt2D body) {
		HjRegion2D hjRegion = newRectangularRegion2D(startA, endAInclusive, startB, endBInclusive);
		forasync2d(chunkedOption, hjRegion, body);
	}

	public static void forall2d(int startA, int endAInclusive, int startB, int endBInclusive,
			HjSuspendingProcedureInt2D body) throws SuspendableException {
		Module1.forall(startA, endAInclusive, startB, endBInclusive, body);
	}

	public static void forall2d(ChunkedOption chunkedOption, int startA, int endAInclusive, int startB,
			int endBInclusive, HjSuspendingProcedureInt2D body) throws SuspendableException {
		HjRegion2D hjRegion = newRectangularRegion2D(startA, endAInclusive, startB, endBInclusive);
		forall2d(chunkedOption, hjRegion, body);
	}

	public static <T> void forseq(Iterable<T> iterable, HjProcedure<T> body) {
		Module1.forseqNb(iterable, body);
	}

	public static <T> void forasync(Iterable<T> iterable, HjProcedure<T> body) {
		Module1.forasyncNb(iterable, body);
	}

	public static <T> void forall(Iterable<T> iterable, HjSuspendingProcedure<T> body) throws SuspendableException {
		Module1.forall(iterable, body);
	}

	public static void async(PhasedOption phasedOption, HjSuspendable body) {
		Module1.asyncPhased(phasedOption.getPhaserPairs(), body);
	}

	public static void forasync(PhasedOption phasedOption, int start, int endInclusive, HjSuspendingProcedureInt1D body)
			throws SuspendableException {
		Module1.forasyncPhased(start, endInclusive, phasedOption.getPhaserPairs(), body);
	}

	public static void forasync(PhasedEmptyOption phasedEmptyOption, int start, int endInclusive,
			HjSuspendingProcedureInt1D body) throws SuspendableException {
		Module1.forasyncPhased(start, endInclusive, body);
	}

	// TODO?
	// public static void forall( PhasedOption phasedOption, int start, int
	// endInclusive, HjSuspendingProcedureInt1D body ) throws
	// SuspendableException {
	// finish(()-> {
	// Module1.forasyncPhased(start, endInclusive,
	// phasedOption.getPhaserPairs(), body);
	// } );
	// }
	public static void forall(PhasedEmptyOption phasedEmptyOption, int start, int endInclusive,
			HjSuspendingProcedureInt1D body) throws SuspendableException {
		Module1.forallPhased(start, endInclusive, body);
	}

	public static void next() throws SuspendableException {
		Module1.next();
	}

	public static void next(SingleOption singleOption) throws SuspendableException {
		// TODO:
		throw new RuntimeException("single option not supported");
	}

	public static void doWait() throws SuspendableException {
		Module1.doWait();
	}

	public static void signal() throws SuspendableException {
		Module1.signal();
	}

	public static void isolated(HjRunnable body) {
		Module2.isolated(body);
	}

	public static void isolated(ObjectBasedIsolationOption objectBasedIsolationOption, HjRunnable body) {
		Module2.isolated(objectBasedIsolationOption.getParticipants(), body);
	}

	public static void doWork(long n) {
		Module0.doWork(n);
	}

	public static HjMetrics abstractMetrics() {
		// NOTE: this is a crude check
		if (preFinalizeCallbackEnterVsLeaveCount.get() > 0) {
			return Module0.abstractMetrics();
		} else {
			throw new IllegalStateException(
					"abstractMetrics() should be called from within launchHabaneroApp's preFinalizeCallback.");
		}
	}

	public static void dumpStatistics() {
		dumpStatistics(abstractMetrics());
	}

	public static void dumpStatistics(HjMetrics metrics) {
		AbstractMetricsManager.dumpStatistics(metrics);
	}

	public static void dumpStatistics(HjMetrics metrics, PrintStream ps) {
		AbstractMetricsManager.dumpStatistics(metrics, ps);
	}

	public static HjPoint newPoint(int... values) {
		return Module1.newPoint(values);
	}

	public static HjRegion1D newRectangularRegion1D(final int pMinInc, final int pMaxInc) {
		return Module1.newRectangularRegion1D(pMinInc, pMaxInc);
	}

	public static HjRegion2D newRectangularRegion2D(final int pMinInc0, final int pMaxInc0, final int pMinInc1,
			final int pMaxInc1) {
		return Module1.newRectangularRegion2D(pMinInc0, pMaxInc0, pMinInc1, pMaxInc1);
	}
	// TODO: support 3D
	// public static HjRegion3D newRectangularRegion3D(
	// final int pMinInc0, final int pMaxInc0,
	// final int pMinInc1, final int pMaxInc1,
	// final int pMinInc2, final int pMaxInc2) {
	// return Module1.newRectangularRegion3D(pMinInc0, pMaxInc0, pMinInc1,
	// pMaxInc1, pMinInc2, pMaxInc2);
	// }

	public static List<HjRegion1D> group(final HjRegion1D hjRegion, final int processorGrid) {
		return Module1.group(hjRegion, processorGrid);
	}

	public static HjRegion1D myGroup(final int groupId, final HjRegion1D hjRegion, final int groupSize) {
		return Module1.myGroup(groupId, hjRegion, groupSize);
	}

	public static void forseq(final HjRegion1D hjRegion, final HjProcedureInt1D body) {
		Module1.forseqNb(hjRegion, body);
	}

	public static void forasync(final HjRegion1D hjRegion, final HjSuspendingProcedureInt1D body) {
		Module1.forasync(hjRegion, body);
	}

	public static List<HjRegion2D> group(final HjRegion2D hjRegion, final int processorGrid0,
			final int processorGrid1) {
		return Module1.group(hjRegion, processorGrid0, processorGrid1);
	}

	public static void forall(final HjRegion1D hjRegion, final HjSuspendingProcedureInt1D body)
			throws SuspendableException {
		Module1.forall(hjRegion, body);
	}

	public static RectangularRegion2D myGroup(final int groupId0, final int groupId1, final HjRegion2D hjRegion,
			final int groupSize0, final int groupSize1) {
		return Module1.myGroup(groupId0, groupId1, hjRegion, groupSize0, groupSize1);
	}

	public static void forseq2d(final HjRegion2D hjRegion, final HjProcedureInt2D body) {
		Module1.forseqNb(hjRegion, body);
	}

	public static void forasync2d(final HjRegion2D hjRegion, final HjProcedureInt2D body) {
		Module1.forasyncNb(hjRegion, body);
	}

	public static void forall2d(final HjRegion2D hjRegion, final HjSuspendingProcedureInt2D body)
			throws SuspendableException {
		Module1.forall(hjRegion, body);
	}

	public static void forasync2d(final ChunkedOption chunkedOption, final HjRegion2D hjRegion,
			final HjProcedureInt2D body) {
		if (chunkedOption.isSizeDecidedBySystem()) {
			Module1.forasyncNbChunked(hjRegion, body);
		} else {
			throw new RuntimeException("chunk size not supported");
		}
	}

	public static void forall2d(final ChunkedOption chunkedOption, final HjRegion2D hjRegion,
			final HjSuspendingProcedureInt2D body) throws SuspendableException {
		if (chunkedOption.isSizeDecidedBySystem()) {
			Module1.forallChunked(hjRegion, body);
		} else {
			throw new RuntimeException("chunk size not supported");
		}
	}
}
