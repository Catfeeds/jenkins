import React from 'react'
import IconList from 'common/list/icon-list'
import 'styles/component/ship/small-list.less'

export default class SmallList extends React.Component{
	render(){
		return (
			<IconList { ...this.props } className = { "small_li" }/>
		)
	}
}