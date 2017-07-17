package com.magitechserver.aepatch.aepatch;

import com.magitechserver.aepatch.aepatch.loader.PatchLoader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

/**
 * Created by Frani on 17/07/2017.
 */
@IFMLLoadingPlugin.Name("AEPatcher")
@IFMLLoadingPlugin.MCVersion("1.10.2")
public class AECore implements IFMLLoadingPlugin {

    public static PatchLoader loader = new PatchLoader();

    public AECore() {
        MixinBootstrap.init();
        try {
            loader.loadPatches();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return AEPatch.class.getName();
    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getSetupClass() {
        return null;
    }

}
