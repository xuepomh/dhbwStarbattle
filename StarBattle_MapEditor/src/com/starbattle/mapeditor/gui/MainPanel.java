package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.gui.control.TileSelection;
import com.starbattle.mapeditor.gui.control.TilePlacement;
import com.starbattle.mapeditor.gui.listener.TilePlacementListener;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.window.ContentPanel;

public class MainPanel extends ContentPanel {

	// panels
	private LayerPanel layerPanel;
	private MapPanel mapPanel;
	private ToolbarPanel toolbarPanel;

	// map
	private Map map;

	public MainPanel() {

		// create default map
		map = new Map((new Dimension(20, 10)));
		// create views
		Repainter repaint = new Repainter();
		layerPanel = new LayerPanel(map, repaint);
		mapPanel = new MapPanel(map, repaint, new TilePlacementAction(),layerPanel.getTilePlacementPreview());
		toolbarPanel = new ToolbarPanel(map, repaint);
		initLayout();
	}

	private void initLayout() {
		view.setLayout(new BorderLayout());
		view.add(layerPanel.getView(), BorderLayout.WEST);
		view.add(mapPanel.getView(), BorderLayout.CENTER);
		view.add(toolbarPanel.getView(), BorderLayout.NORTH);

	}

	private class TilePlacementAction implements TilePlacementListener {

		@Override
		public void placeTile(int mx, int my) {
			// TODO Auto-generated method stub
			MapLayer layer = layerPanel.getSelectedLayer();
			TileSelection selection = layerPanel.getTileSelection();
			new TilePlacement(layer, selection, mx, my);
			mapPanel.getView().repaint();
		}

	}

	private class Repainter implements RepaintListener {

		@Override
		public void repaintAll() {
			view.repaint();
		}

		@Override
		public void repaintMap() {
			mapPanel.getView().repaint();
		}

		@Override
		public void refreshLayer() {
			layerPanel.updateLayers();
		}

	}

}