import React from 'react'
import { FormCell, TextArea, CellBody } from 'react-weui'

export default class WeuiSelectList extends React.Component{
	render(){
		return(
			<FormCell>
				<CellBody>
					<TextArea { ...this.props }/>
				</CellBody>
			</FormCell>
		)
	}
}