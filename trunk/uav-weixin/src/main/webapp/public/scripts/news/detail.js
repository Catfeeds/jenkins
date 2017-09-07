import React from 'react'
import Tool from 'utils/tool'
import { Panel, PanelHeader, PanelBody, MediaBox, MediaBoxHeader, MediaBoxBody, MediaBoxDescription, Flex, FlexItem, Cells, Cell, CellBody, CellFooter } from 'react-weui'
import "styles/news/detail.less"

export default class NewsDetailApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			data : {}
		};
	}
	componentDidMount(){
		Tool.get(`${ config.ajaxPath }/portal/page/${ this.props.match.params.id }` )
		.then( json => this.setState({ data : json.data }))
	}
	render(){
		let {
			data
		} = this.state,
		attribute = data.attribute || {},
		attachments = data.attachments || {};
		return (
			<div id= "news_detail_app">
				<Panel>
					<PanelBody>
						<MediaBox>
							<MediaBoxBody>{ attribute.title }</MediaBoxBody>
							<MediaBoxDescription>
								<Flex style = {{ marginTop : "10px" }}>
									<FlexItem style = {{ textAlign : "left" }}>{ attribute.createTime }</FlexItem>
									<FlexItem style = {{ textAlign : "right" }}>e飞服务</FlexItem>
								</Flex>
							</MediaBoxDescription>
						</MediaBox>
					</PanelBody>
				</Panel>
				<Panel>
					{ data.content && <PanelBody dangerouslySetInnerHTML = {{ __html : data.content }}></PanelBody> }
				</Panel>
			</div>
		)
	}
}