package edu.wustl.cse231s.raytrace;

import java.awt.BorderLayout;
import java.io.File;
import java.net.URL;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.sunflow.SunflowAPI;

import edu.wustl.cse231s.raytrace.runtime.RtImageSampler;
import edu.wustl.cse231s.raytrace.runtime.RtVizPanel;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum RayTraceUtils {
	;
	public static void launch(RayTracer rayTracer, URL url) {
		SwingUtilities.invokeLater(() -> {
			RtVizPanel panel = new RtVizPanel();
			RtImageSampler imageSampler = new RtImageSampler(rayTracer);

			JFrame frame = new JFrame("raytrace");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);

			String path = url.getFile();
			File file = new File(path);
			if (file.exists()) {
				new Thread(() -> {
					SunflowAPI api = new SunflowAPI();
					api.parse(path);
					api.build();
					api.options(SunflowAPI.DEFAULT_OPTIONS);
					api.render(SunflowAPI.DEFAULT_OPTIONS, panel, imageSampler);
				}).start();
			}
		});
	}

	public static ConcurrentLinkedDeque<Section> createSections(int xMin, int yMin, int xMaxExclusive,
			int yMaxExclusive, int size) {
		ConcurrentLinkedDeque<Section> queue = new ConcurrentLinkedDeque<>();
		for (int y = yMin; y < yMaxExclusive; y += size) {
			for (int x = xMin; x < xMaxExclusive; x += size) {
				queue.offerFirst(new Section(x, y, Math.min(x + size, xMaxExclusive), Math.min(y + size, yMaxExclusive)));
			}
		}
		return queue;
	}
}
