package gregicality.science.common.pipelike.pressure.net;

import gregicality.science.common.pipelike.pressure.tile.TileEntityPressurePipe;
import gregtech.api.pipenet.PipeNetWalker;
import gregtech.api.pipenet.tile.IPipeTile;
import gregtech.common.pipelike.optical.tile.TileEntityOpticalPipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PressureNetWalker extends PipeNetWalker<TileEntityPressurePipe> {

    private double pressure = -1;

    public static void checkPressure(World world, BlockPos start, double pressure) {
        PressureNetWalker walker = new PressureNetWalker(world, start, 0);
        walker.pressure = pressure;
        walker.traversePipeNet();
    }

    protected PressureNetWalker(World world, BlockPos sourcePipe, int walkedBlocks) {
        super(world, sourcePipe, walkedBlocks);
    }

    @Override
    protected PipeNetWalker<TileEntityPressurePipe> createSubWalker(World world, EnumFacing enumFacing, BlockPos blockPos, int i) {
        PressureNetWalker walker = new PressureNetWalker(world, blockPos, i);
        walker.pressure = pressure;
        return walker;
    }

    @Override
    protected void checkPipe(TileEntityPressurePipe pipeTile, BlockPos pos) {
        ((TileEntityPressurePipe) pipeTile).checkPressure(pressure);
    }

    @Override
    protected void checkNeighbour(TileEntityPressurePipe pipeTile, BlockPos pipePos, EnumFacing faceToNeighbour, @org.jetbrains.annotations.Nullable TileEntity neighbourTile) {

    }

    @Override
    protected Class<TileEntityPressurePipe> getBasePipeClass() {
        return TileEntityPressurePipe.class;
    }

}
