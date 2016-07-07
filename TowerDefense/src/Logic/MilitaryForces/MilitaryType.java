package Logic.MilitaryForces;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;

/**
 * Created by akhavan on 2016-06-09.
 */
public enum MilitaryType {
    BASIC,
    FIRE,
    TREE,
    LIGHT,
    DARK;

    @NotNull
    public Class<?> getTowerType(){
        switch (this){
            case BASIC:
                return BasicTower.class;
            case FIRE:
                return FireTower.class;
            case TREE:
                return TreeTower.class;
            case LIGHT:
                return LightTower.class;
            case DARK:
                return DarkTower.class;
        }
        return null;
    }

    @NotNull
    public Integer initialPrice(){
        try {
            Field f = this.getTowerType().getDeclaredField("INITIAL_PRICE");
            return (Integer) f.get(this.getClass());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
