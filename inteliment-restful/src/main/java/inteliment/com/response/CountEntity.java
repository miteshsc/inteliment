package inteliment.com.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author Mitesh Chavda
 * @version 1.0
 *
 */
@JsonPropertyOrder({ "text", "count" })
public class CountEntity implements Serializable {

    public String text;

    public int count;

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public int getCount() {
	return count;
    }

    public void setCount(int count) {
	this.count = count;
    }

}
