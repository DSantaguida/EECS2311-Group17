package enamel;

public abstract class Event {
	
	public abstract String getData();
	public abstract void setData(String data);
	
	
	@Override
	public String toString() {
		return this.getData();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Event other = (Event) obj;
		if (getData() == null) {
			if (other.getData() != null) {
				return false;
			}
		} else if (!getData().equals(other.getData())) {
			return false;
		}
		return true;
	}
	
	
}
