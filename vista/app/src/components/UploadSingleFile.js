import React, {Component} from 'react';

import {Upload} from 'antd';
import {config} from '../utils/api';
import { buildFileList } from '../utils/utils';

class UploadSingleFile extends Component {
  constructor (props) {
    super(props);

    this.state = {
      fileList: props.preloadedFile ? buildFileList(props.preloadedFile) : []
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
    return (
      <Upload
        {...this.props}
        fileList={this.state.fileList}
        headers={config().headers}
        onChange={this.handleChange}
      />
    );
  }

}

export default UploadSingleFile;
