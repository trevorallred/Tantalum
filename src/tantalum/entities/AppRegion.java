package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "dd_region")
public class AppRegion extends BaseNamedTable {

	private RegionType regionType;
	private AppRegion parent;

	public RegionType getRegionType() {
		return regionType;
	}

	public void setRegionType(RegionType regionType) {
		this.regionType = regionType;
	}

	public AppRegion getParent() {
		return parent;
	}

	public void setParent(AppRegion parent) {
		this.parent = parent;
	}

}
