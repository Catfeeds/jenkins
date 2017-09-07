import React from 'react'
import { FormCell, CellHeader, CellBody, Label, Select } from 'react-weui'

export default class WeuiSelectList extends React.Component{
	render(){
		let {
			label,
			data,
		} = this.props;
		return(
			<FormCell select selectPos = "after">
				<CellHeader>
					<Label>{label}</Label>
				</CellHeader>
				<CellBody>
					<Select data = { data }/>
				</CellBody>
			</FormCell>
		)
	}
}