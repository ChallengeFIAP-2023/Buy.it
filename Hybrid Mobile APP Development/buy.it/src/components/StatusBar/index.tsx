import {
  Platform,
  View,
  StatusBar as RNStatusBar,
  StatusBarProps as RNStatusBarProps,
  StyleSheet,
} from 'react-native';

// Theme import
import theme from '@theme/index';

// Util import
import { getStatusBarHeight } from '@utils/getStatusBarHeight';

export interface Props extends RNStatusBarProps {
  /** skips iOS */
  noForce?: boolean;
  backgroundColor?: string;
  dark?: boolean;
}

// Styles
const style = StyleSheet.create({
  statusBar: {
    height: getStatusBarHeight(),
    zIndex: 999,
  },
});

export const StatusBar: React.FC<Props> = ({
  noForce,
  backgroundColor = theme.COLORS.GRAY_700,
  dark,
  ...rest
}) => {
  return (
    <>
      <RNStatusBar
        animated
        backgroundColor={backgroundColor}
        barStyle={dark ? 'dark-content' : 'light-content'}
        {...(Platform.OS === 'ios' && {
          translucent: true,
        })}
        {...rest}
      />
      {Platform.OS === 'ios' && (
        <View
          style={[
            style.statusBar,
            {
              backgroundColor,
              display: noForce ? 'none' : 'flex',
            },
          ]}
        />
      )}
    </>
  );
};
