import React from 'react';
import {
  Platform,
  View,
  StatusBar as RNStatusBar,
  StatusBarProps,
  StyleSheet,
} from 'react-native';

// Util import
import { getStatusBarHeight } from '@utils/getStatusBarHeight';

// Interfaces
export interface Props extends StatusBarProps {
  /** skips iOS */
  noForce?: boolean;
  dark?: boolean;
}

// Style import
const style = StyleSheet.create({
  statusBar: {
    height: getStatusBarHeight(),
    zIndex: 999,
  },
});

export function StatusBar({
  noForce,
  backgroundColor,
  dark,
  ...rest
}: Props) {
  return (
    <>
      <RNStatusBar
        animated
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
