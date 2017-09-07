import React from 'react'
import { Form, FormCell, CellHeader, CellBody, Label, Input } from 'react-weui'

export default class WeuiSelectList extends React.Component{
	render(){
		let {
			label,
			data,
			type,
		} = this.props;
		return(
			<FormCell>
				<CellHeader>
					<Label>{label}</Label>
				</CellHeader>
				<CellBody>
					<Input type = { type || "text" } { ...this.props } />
				</CellBody>
			</FormCell>
		)
	}
}