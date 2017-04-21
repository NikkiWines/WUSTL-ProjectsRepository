/**
 * Homework 5 assignment: Do-It-Yourself. In this assignment, you will apply the
 * parallel programming concepts that you've learned in the Habanero-Java
 * library and apply them to Java's built-in classes.
 * <p>
 * This entire assignment is test-based. That means that after you finish each
 * part of this assignment, you can (and should) test it with a JUnit test case.
 * These tests can be found in the hw5.test subpackages. You should not modify
 * any of the test files, but feel free to take a look at them and try to
 * understand what they're doing. Note that passing a test does not guarantee
 * that your code is bug-free; the tests are there as a guide.
 * <p>
 * Here are the parts of this assignment that you need to complete in the order
 * that you should complete and test them:
 * <ol>
 * <li>Nucleobase counting
 * <ol type="a">
 * <li>{@link hw5.nucleobase.NucleobaseCounter} (test with
 * {@link hw5.test.part1.ASequentialNucleobaseCountTest})
 * <li>{@link hw5.slice.Slice} (test with {@link hw5.test.part1.BSliceTest})
 * <li>{@link hw5.nucleobase.ThreadNucleobaseCounter} (test with
 * {@link hw5.test.part1.CThreadNucleobaseCountTest})
 * <li>{@link hw5.nucleobase.ExecutorNucleobaseCounter} (test with
 * {@link hw5.test.part1.DExecutorNucleobaseCountTest})
 * </ol>
 * <li>Quicksort
 * <ol type="a">
 * <li>{@link hw5.quicksort.Quicksorter} (test with
 * {@link hw5.test.part2.ASequentialQuicksortTest})
 * <li>{@link hw5.quicksort.ThreadQuicksorter} (test with
 * {@link hw5.test.part2.BThreadQuicksortTest})
 * <li>{@link hw5.quicksort.ExecutorQuicksorter} (test with
 * {@link hw5.test.part2.CExecutorTest})
 * </ol>
 * <li>Write your own <code>finish</code> and <code>async</code>
 * <ol type="a">
 * <li>{@link hw5.framework.thread.ThreadFramework} and
 * <code>ThreadFinishContext</code> (test with
 * {@link hw5.test.part3.AThreadFrameworkAsyncFinishTest})
 * <li>{@link hw5.framework.executor.ExecutorFramework} and
 * <code>ExecutorFinishContest</code> (test with
 * {@link hw5.test.part3.BExecutorFrameworkAsyncFinishTest})
 * <li>{@link hw5.framework.FinishContext} (test with
 * {@link hw5.test.part3.CFrameworkForAsyncTest})
 * <li>{@link hw5.framework.Framework} (test with
 * {@link hw5.test.part3.DFrameworkForAllTest})
 * </ol>
 * <li>Use your own <code>finish</code> and <code>async</code>
 * <ol type="a">
 * <li>{@link hw5.nucleobase.FrameworkNucleobaseCounter} (test with
 * {@link hw5.test.part4.AFrameworkNucleobaseCounterTest})
 * <li>{@link hw5.quicksort.FrameworkQuicksorter} (test with
 * {@link hw5.test.part4.BFrameworkQuicksortTest})
 * </ol>
 * <li>{@link hw5.jalapeno.Jalapeno}
 * </ol>
 * 
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 * @author Finn Voichick
 */
package hw5;
