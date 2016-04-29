package covers1624.jarrm.reference;

public enum GuiIds {

	ALLOY_FURNACE,
	PROJECT_TABLE,
	CANVAS_BAG,
    UNKNOWN;


    public static GuiIds parse(int ID){
        try{
            return values()[ID];
        } catch (Exception ignored){
            return UNKNOWN;
        }
    }

}
