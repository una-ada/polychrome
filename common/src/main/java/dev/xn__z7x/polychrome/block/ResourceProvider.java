package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.Polychrome;
import dev.xn__z7x.polychrome.util.Materials;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicClientResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.PackGenerationStrategy;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class ResourceProvider extends DynamicClientResourceProvider{

  public ResourceProvider() {
    super(Polychrome.id("dynamic_resources"), PackGenerationStrategy.CACHED);
  }

  @Override
  protected Collection<String> gatherSupportedNamespaces(){
    return List.of("minecraft", Polychrome.MOD_ID);
  }

  @Override
  protected void regenerateDynamicAssets(Consumer<ResourceGenTask> executor){
    executor.accept(((manager, sink) -> {
      Map<String, Palette> sources = new HashMap<>();
      for(var material : Materials.all()){
        try(
          TextureImage source = TextureImage.open(manager, RPUtils.findFirstBlockTextureLocation(manager, Materials.asBlock(material)))
        ){
          sources.put(material, Palette.fromImage(source));
        } catch(IOException e){
          throw new RuntimeException(e);
        }
      }
      for(var model : PolychromeBlocks.blocks){
        Map<TextureSlot, ResourceLocation> map = model.mappingFunction.apply("template");
        map.forEach((slot, template) -> {
          try(TextureImage base = TextureImage.open(manager, template)){
            Respriter respriter = Respriter.of(base);
            model.forEach((material, block) -> {
              ResourceLocation outputUri = model.mappingFunction.apply(material).get(slot);
              if(!sink.alreadyHasAssetAtLocation(manager, outputUri) && !Objects.equals(material, "polychrome")){
                sink.addTexture(outputUri, respriter.recolor(sources.get(material)));
              }
            });
          } catch(Exception err) {
            throw new IllegalStateException(err);
          }
        });
      }
    }));
  }
}
