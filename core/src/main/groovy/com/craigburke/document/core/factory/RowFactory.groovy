package com.craigburke.document.core.factory

import com.craigburke.document.core.Row

class RowFactory extends AbstractFactory {
	
	boolean isLeaf() { false }
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, node, Map attributes) { false }
	
	def newInstance(FactoryBuilderSupport builder, name, value, Map attributes) {
		builder.tablePosition.cell = 0
		Row row = new Row(attributes)
		
		row.position = builder.tablePosition.row
		row.font = row.font ?: builder.current.font.clone()
        if (builder.addRowToTable) {
            builder.addRowToTable(row, builder.current)
        }

		row
	}

	void setChild(FactoryBuilderSupport builder, row, cell) {
		cell.parent = row
		row.cells << cell
		builder.tablePosition.cell++
	}
	
	void onNodeCompleted(FactoryBuilderSupport builder, parent, child) {
		if (builder.onRowComplete) {
			builder.onRowComplete(child, parent)
		}
   	}
	
}