list n_LeavesGraphics
GETSTATIC net/minecraft/init/Blocks.field_150362_t : Lnet/minecraft/block/BlockLeaves;
ALOAD 0
GETFIELD net/minecraft/client/renderer/RenderGlobal.field_72777_q : Lnet/minecraft/client/Minecraft;
GETFIELD net/minecraft/client/Minecraft.field_71474_y : Lnet/minecraft/client/settings/GameSettings;
GETFIELD net/minecraft/client/settings/GameSettings.field_74347_j : Z
INVOKEVIRTUAL net/minecraft/block/BlockLeaves.func_150122_b (Z)V
GETSTATIC net/minecraft/init/Blocks.field_150361_u : Lnet/minecraft/block/BlockLeaves;
ALOAD 0
GETFIELD net/minecraft/client/renderer/RenderGlobal.field_72777_q : Lnet/minecraft/client/Minecraft;
GETFIELD net/minecraft/client/Minecraft.field_71474_y : Lnet/minecraft/client/settings/GameSettings;
GETFIELD net/minecraft/client/settings/GameSettings.field_74347_j : Z
INVOKEVIRTUAL net/minecraft/block/BlockLeaves.func_150122_b (Z)V

list i_LeavesGraphics
INVOKESTATIC net/minecraft/client/Minecraft.func_71410_x ()Lnet/minecraft/client/Minecraft;
GETFIELD net/minecraft/client/Minecraft.field_71474_y : Lnet/minecraft/client/settings/GameSettings;
GETFIELD net/minecraft/client/settings/GameSettings.field_74347_j : Z
INVOKESTATIC covers1624/jarrm/asm/ASMHooks.setGraphicsLevelCallBack (Z)V
