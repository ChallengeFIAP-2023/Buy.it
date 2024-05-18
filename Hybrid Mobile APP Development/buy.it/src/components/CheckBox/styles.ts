import theme from '@theme/index';
import { TextStyle, ViewStyle } from 'react-native';

export const textStyle: TextStyle = {
  fontFamily: theme.FONT_FAMILY.RALEWAY.BOLD,
  color: theme.COLORS.GRAY_200,
  fontSize: theme.FONT_SIZE.MD,
};

export const containerStyle: ViewStyle = {
  width: "100%",
  backgroundColor: "transparent",
  padding: 0,
  marginVertical: 10,
  marginHorizontal: 0
};
