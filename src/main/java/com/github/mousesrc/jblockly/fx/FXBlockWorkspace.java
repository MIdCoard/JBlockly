package com.github.mousesrc.jblockly.fx;

import com.github.mousesrc.jblockly.fx.util.FXHelper;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class FXBlockWorkspace extends Control implements BlockWorkspaceHolder, Connectable{
	
	private ReadOnlyObjectWrapper<FXBlockWorkspace> workspace = new ReadOnlyObjectWrapper<FXBlockWorkspace>(this);
	public ReadOnlyObjectProperty<FXBlockWorkspace> workspaceProperty() {return workspace.getReadOnlyProperty();}

	private final ObservableList<FXBlock> blocks = FXCollections.observableArrayList();
	
	private static final String DEFAULT_STYLE_CLASS = "block-workspace";
	
	public FXBlockWorkspace() {
		getStyleClass().addAll(DEFAULT_STYLE_CLASS);
	}
	
	public ObservableList<FXBlock> getBlocks(){
		return blocks;
	}
	
	@Override
	public boolean connect(FXBlock block, Bounds bounds) {
		return getBlocks().stream()
				.anyMatch(b -> b.connect(block, FXHelper.subtractBounds2D(bounds, b.getLayoutX(), b.getLayoutY())));
	}
	
	@Override
	protected Skin<?> createDefaultSkin() {
		return new FXBlockWorkspaceSkin(this);
	}
}
