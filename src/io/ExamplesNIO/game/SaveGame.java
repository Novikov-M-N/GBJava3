package io.ExamplesNIO.game;

import java.io.Serializable;
import java.util.Arrays;

public class SaveGame implements Serializable {
    private static final long serialVersion = 1L;

    private transient String[] territoriesInfo;
    private String[] resourcesInfo;
    private String[] diplomaticInfo;

    public SaveGame(String[] territoriesInfo, String[] resourcesInfo, String[] diplomaticInfo) {
        this.territoriesInfo = territoriesInfo;
        this.resourcesInfo = resourcesInfo;
        this.diplomaticInfo = diplomaticInfo;
    }

    public String[] getTerritoriesInfo() {
        return territoriesInfo;
    }

    public void setTerritoriesInfo(String[] territoriesInfo) {
        this.territoriesInfo = territoriesInfo;
    }

    public String[] getResourcesInfo() {
        return resourcesInfo;
    }

    public void setResourcesInfo(String[] resourcesInfo) {
        this.resourcesInfo = resourcesInfo;
    }

    public String[] getDiplomaticInfo() {
        return diplomaticInfo;
    }

    public void setDiplomaticInfo(String[] diplomaticInfo) {
        this.diplomaticInfo = diplomaticInfo;
    }

    @Override
    public String toString() {
        return "SaveGame{" +
                "\n territoriesInfo=" + Arrays.toString(territoriesInfo) +
                "\n , resourcesInfo=" + Arrays.toString(resourcesInfo) +
                "\n , diplomaticInfo=" + Arrays.toString(diplomaticInfo) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveGame saveGame = (SaveGame) o;
        return Arrays.equals(territoriesInfo, saveGame.territoriesInfo) &&
                Arrays.equals(resourcesInfo, saveGame.resourcesInfo) &&
                Arrays.equals(diplomaticInfo, saveGame.diplomaticInfo);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(territoriesInfo);
        result = 31 * result + Arrays.hashCode(resourcesInfo);
        result = 31 * result + Arrays.hashCode(diplomaticInfo);
        return result;
    }
}
