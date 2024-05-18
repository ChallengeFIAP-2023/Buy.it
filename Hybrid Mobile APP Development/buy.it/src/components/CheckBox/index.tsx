import React from 'react';
import { CheckBox, CheckBoxProps } from '@rneui/themed';

// Theme import
import theme from '@theme/index';
import { containerStyle, textStyle } from './styles';

export function CustomCheckBox({ checked, title, onPress, ...rest }: CheckBoxProps) {
  return (
    <CheckBox
      title={title}
      textStyle={textStyle}
      containerStyle={containerStyle}
      checkedColor={theme.COLORS.PRIMARY}
      uncheckedColor={theme.COLORS.GRAY_400}
      checked={checked}
      onPress={onPress}
    />
  );
}
