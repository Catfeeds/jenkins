import React from 'react'
import IconList from 'common/list/icon-list'
import OptionalList from 'common/list/optional-list'
import Layout from 'common/layout'
import ButtonGroup from 'common/button-group'
import Button from 'common/button'
import Tool from 'utils/tool'

import 'styles/common/select-list.less'

export default class SelectList extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			show : false,
			selected : null,
		};
	}
	hideHandle(){
		this.setState( { show : false } );
	}
	showHandle(){
		let {
			options
		} = this.props;
		if( !options || options.length === 0 ){
			Tool.alert("暂无数据选择")
			return;
		}
		this.setState( { show : true } );
	}
	onSubmitHandle( selected ){
		let {
			click,
			options,
		} = this.props;
		typeof click === "function" && click( options[ selected ] );
		this.hideHandle();
	}
	render(){
		let {
				options,
				dispayText,
				rightIcon,
				value,
				width,

			} = this.props,
			{
				selected,
				show,
			} = this.state,
			_name = ( !!value && !!value.name ? value.name : "请选择" ),
			_value = ( !!value && value.value );
		return (
			<div className = "__select_list_wrap">
				<IconList { ...this.props } text = { _name } className = "__select_list" click = { () => this.showHandle() } />
				<Layout style = {{ maxHeight : '90%' }} show = { show } hideHandle = { () => this.hideHandle() }>
					<div style = {{ width : `${ width }px`}} className = "__option_content">
						{
							!!options && options.map( ( v, k ) => {
								return (
									<OptionalList click = { () => this.onSubmitHandle( k ) } label = { v.name } text = { !!dispayText && v.text } selected = { v.value === _value } key = { `options_${ k }`}/>
								)
							})
						}
					</div>
				</Layout>
			</div>
		)
	}
}