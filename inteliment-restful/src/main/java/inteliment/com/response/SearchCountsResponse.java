package inteliment.com.response;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Mitesh Chavda
 * @version 1.0
 *
 */
public class SearchCountsResponse {
    public List<Map<String, Integer>> counts;

    public List<Map<String, Integer>> getCounts() {
	return counts;
    }

    public void setCounts(List<Map<String, Integer>> counts) {
	this.counts = counts;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((counts == null) ? 0 : counts.hashCode());
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
	SearchCountsResponse other = (SearchCountsResponse) obj;
	if (counts == null) {
	    if (other.counts != null)
		return false;
	} else if (!counts.equals(other.counts))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "SearchCountsResponse [counts=" + counts + "]";
    }

}
