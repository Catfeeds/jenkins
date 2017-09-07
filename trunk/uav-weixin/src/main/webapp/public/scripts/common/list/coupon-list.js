import React from 'react'
import IconList from 'common/list/icon-list'
import 'styles/common/list/coupon-list.less'

const couponType = {
	1 : {
		name : '满减卷',
		className : 't1',
	},
	2 : {
		name : '折扣卷',
		className : 't2',
	},
}
export default class CouponList extends React.Component{
	render(){
		let {
				type,
				label,
				click,
			} = this.props,
			{
				name,
				className,
			} = couponType[ type ] || {},
			_label = <p>{ label }<span className = { `tag ${ className }` }>{ name }</span></p>
		return <IconList { ...this.props } className = "__coupon_list" label = { _label } rightIcon = "icon_forward"/>
	}
}