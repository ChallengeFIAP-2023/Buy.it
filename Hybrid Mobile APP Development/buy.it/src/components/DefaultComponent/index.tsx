import React from 'react';

// Component import
import { Header, Props as HeaderProps } from '../Header';
import { Highlight, Props as HighlightProps } from '../Highlight';
import { StatusBar, Props as StatusBarProps } from '../StatusBar';

interface Props {
  statusBarProps?: StatusBarProps;
  headerProps?: HeaderProps;
  highlightProps?: HighlightProps;
}

export function DefaultComponent({
  statusBarProps,
  headerProps,
  highlightProps
}: Props) {
  return (
    <>
      <StatusBar {...statusBarProps} />
      <Header {...headerProps} />
      {highlightProps && <Highlight {...highlightProps} />}
    </>
  );
}
