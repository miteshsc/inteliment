package inteliment.com.request;

import java.util.List;

/**
 * 
 * @author Mitesh Chavda
 * @version 1.0
 *
 */
public class SearchText {
    public List<String> searchText;

    public List<String> getSearchText() {
	return searchText;
    }

    public void setSearchText(List<String> searchText) {
	this.searchText = searchText;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((searchText == null) ? 0 : searchText.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SearchText other = (SearchText) obj;
	if (searchText == null) {
	    if (other.searchText != null)
		return false;
	} else if (!searchText.equals(other.searchText))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "SearchText [searchText=" + searchText + "]";
    }

}
