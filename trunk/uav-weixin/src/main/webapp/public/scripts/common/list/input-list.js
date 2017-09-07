import React from 'react'
import List from 'common/list'
import 'styles/common/input-list.less'

export default class InputList extends React.Component{
	render(){
		let {
			label,
			labelWidth,
			id,
		} = this.props;

		return (
			<List className = "__input_list">
				<label style = { {  display : "block", width : labelWidth } } htmlFor = { id }>{ label }</label>
				<input id = { id } { ...this.props }/>
			</List>
		)
	}
}