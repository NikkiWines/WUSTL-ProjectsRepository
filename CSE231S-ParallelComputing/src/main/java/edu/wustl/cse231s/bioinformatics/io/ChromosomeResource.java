package edu.wustl.cse231s.bioinformatics.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum ChromosomeResource {
	X(43_479_439L), 
	Y(7_362_325L),;
	private ChromosomeResource(long fileLength) {
		this.fileLength = fileLength;
	}

	public long getFileLength() {
		return this.fileLength;
	}

	public URL getUrl() {
		try {
			return new URL(	"ftp://ftp.ncbi.nlm.nih.gov/genomes/all/GCA/000/001/405/GCA_000001405.25_GRCh38.p10/GCA_000001405.25_GRCh38.p10_assembly_structure/Primary_Assembly/assembled_chromosomes/FASTA/chr"
							+ this.name() + ".fna.gz");
		} catch (MalformedURLException murle) {
			throw new Error(murle);
		}
	}

	private final long fileLength;
}
