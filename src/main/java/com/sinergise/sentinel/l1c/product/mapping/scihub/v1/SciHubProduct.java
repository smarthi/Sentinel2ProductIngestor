package com.sinergise.sentinel.l1c.product.mapping.scihub.v1;

import java.io.File;
import java.io.FilenameFilter;

import com.sinergise.sentinel.l1c.product.mapping.scihub.AbstractSciHubProduct;
import com.sinergise.sentinel.l1c.product.mapping.scihub.AbstractSciHubProductTile;
import com.sinergise.sentinel.scihub.SciHubEntry;

public class SciHubProduct extends AbstractSciHubProduct {

	public SciHubProduct(File productBase, SciHubEntry sciHubEntry) {
		super(productBase, sciHubEntry);
	}

	@Override
	protected File getMetadataFile(File productBase) {
		File metadataFiles[] = productBase.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("xml") && name.contains("OPER_MTD_SAFL1C");
			}
		});
		if (metadataFiles.length!=1) {
			throw new RuntimeException("Couldn't find product metadata.xml file!");
		}
		return metadataFiles[0];
	}

	@Override
	protected AbstractSciHubProductTile createTile(AbstractSciHubProduct sciHubProduct, File tileDir) {
		return new SciHubProductTile(sciHubProduct, tileDir);
	}

}
