package org.formation;

class ProjectVersion implements Serializable {
    Integer majorNumber
    Integer minorNumber
    String classifier = 'beta'

    ProjectVersion(majorNumber, minorNumber, classifier) {

		this.minorNumber = Integer.parseInt(minorNumber)
		this.majorNumber = Integer.parseInt(majorNumber)
        if ( classifier )
		    this.classifier = classifier

	}

    @Override
    String toString() {
         "$majorNumber.$minorNumber.$classifier" 
    }
}