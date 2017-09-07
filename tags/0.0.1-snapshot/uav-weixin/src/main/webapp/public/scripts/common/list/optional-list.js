import React from 'react'
import IconList from 'common/list/icon-list'
import 'styles/common/optional-list.less'

export default class OptionalList extends React.Component{
	selectHandle(){
		let {
			click,
		} = this.props;
		typeof click === "function" && click()
	}
	render(){
		return (
			<IconList { ...this.props } className = "__optional_list" click = { () => this.selectHandle() } leftIcon = { !!this.props.selected ? "icon_selected" : "icon_selected_miss"  }  />
		)
	}
}