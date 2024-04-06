import React from 'react';

import { StatusFilterType, tabs } from './constants';

interface Props {
  activeStep: StatusFilterType | undefined;
  handleSelectTab: React.Dispatch<
    React.SetStateAction<StatusFilterType>
  >;
}

import { BottomTab, Container, Tab, TabText } from './styles';

export const Tabs: React.FC<Props> = ({ activeStep, handleSelectTab }) => {
  return (
    <Container>
      {tabs.map(tab => (
        <Tab onPress={() => handleSelectTab(tab)} key={tab}>
          <TabText active={activeStep === tab}>{tab}</TabText>
          <BottomTab active={activeStep === tab} />
        </Tab>
      ))}
    </Container>
  );
};
