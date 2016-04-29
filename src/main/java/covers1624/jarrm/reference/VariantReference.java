package covers1624.jarrm.reference;

import com.google.common.collect.Lists;
import covers1624.lib.util.ArrayUtils;

import java.util.List;

/**
 * Created by covers1624 on 2/6/2016.
 */
public class VariantReference {

    public static final String[] colours = new String[] { "white", "orange", "magenta", "lightBlue", "yellow", "lime", "pink", "gray", "lightGray", "cyan", "purple", "blue", "brown", "green", "red", "black" };

    public static final String[] oreNames = new String[] { "oreruby", "oreGreenSapphire", "oreSapphire", "oreSilver", "oreTin", "oreCopper", "oreNikolite", "oreTungsten" };
    public static final List<String> oreNamesList = Lists.newArrayList(ArrayUtils.arrayToLowercase(oreNames));

    //TODO Reorder these and add marble variants of circle and paver.
    public static final String[] stoneNames = new String[] { "marble", "basalt", "marbleBrick", "basaltCobble", "basaltBrick", "basaltCircle", "basaltPaver" };
    public static final List<String> stoneNamesList = Lists.newArrayList(ArrayUtils.arrayToLowercase(stoneNames));

    public static final String[] applianceNames = new String[] { "alloyFurnace" };
    public static final List<String> applianceNamesList = Lists.newArrayList(ArrayUtils.arrayToLowercase(applianceNames));

    public static final String[] plantableNames = new String[] { "rubberSapling", "indigoFlower" };
    public static final List<String> plantableNamesList = Lists.newArrayList(ArrayUtils.arrayToLowercase(plantableNames));
}
