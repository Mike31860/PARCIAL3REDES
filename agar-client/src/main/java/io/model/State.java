package io.model;


public enum State{


    Lost,
    Playing,
    Error, Join, streaming,streaming2, Chat ;
    
    private String message;

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

	public String getMessage() {
		return message;
		
	}

}