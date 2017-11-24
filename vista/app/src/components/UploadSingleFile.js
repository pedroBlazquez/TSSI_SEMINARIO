import React, {Component} from 'react';

import {Upload} from 'antd';

class UploadSingleFile extends Component {
  constructor (props) {
    super(props);

    this.state = {
      fileList: []
    };
  }
  
  handleChange = (info) => {
    let fileList = info.fileList;
    fileList = fileList.slice(-1);
    this.setState({ fileList });

    if (typeof this.props.onChange === 'function') {
      this.props.onChange(info);
    }
  }

  render () {
    return (<Upload {...this.props} fileList={this.state.fileList} onChange={this.handleChange} />)
  }

}

export default UploadSingleFile;
