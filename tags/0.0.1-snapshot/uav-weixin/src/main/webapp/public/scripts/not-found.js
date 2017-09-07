import React from 'react'
import telBS64 from 'images/tel'
import addressBS64 from 'images/address'
import { Panel, PanelBody, Icon, MediaBox, MediaBoxHeader, MediaBoxBody, MediaBoxDescription } from 'react-weui'
import "styles/not-found.less"

export default class NotFoundApp extends React.Component{
	render(){
		return (
			<div id = "not_found_app">
				<div className = "context">
					<Icon value = "warn" size = "large" />
					<div className = "title">{ this.props.__title ? `${ this.props.__title }模块正在开发中...` : "页面不存在" }</div>
					<p className = "desc">如有需求请联系e飞服务商</p>
				</div>
				<Panel>
					<PanelBody>
						<MediaBox type = "appmsg">
							<MediaBoxHeader>
								<img src={ addressBS64 }/>
							</MediaBoxHeader>
							<MediaBoxBody>
								广州市越秀区永福路8号永怡新都605室
							</MediaBoxBody>
						</MediaBox>
					</PanelBody>
					<PanelBody>
						<MediaBox type = "appmsg">
							<MediaBoxHeader>
								<img src={ telBS64 }/>
							</MediaBoxHeader>
							<MediaBoxBody>
								020-82258167
							</MediaBoxBody>
						</MediaBox>
					</PanelBody>					
				</Panel>
			</div>
		)
	}
}