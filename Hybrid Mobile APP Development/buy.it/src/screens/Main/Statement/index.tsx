import { useState } from 'react';
import { NativeStackScreenProps } from '@react-navigation/native-stack';

// Type import
import { MainRoutes } from '..';

// Component import
import {
  DecreasingContainer,
  DefaultComponent,
  WrapperPage,
} from '@components/index';
import { Tabs } from './Tabs';

import { StatusFilterType } from './Tabs/constants';

// Style import
import { Quotes } from './styles';
import { QuoteItem } from './QuoteItem';

export function Statement({
  navigation,
}: NativeStackScreenProps<MainRoutes, 'Profile'>) {
  const [activeStep, setActiveStep] = useState<StatusFilterType>();

  return (
    <WrapperPage>
      <DefaultComponent
        highlightProps={{
          title: 'Aqui você encontra suas cotações',
          highlightedText: 'cotações',
        }}
        headerProps={{ goBack: () => navigation.goBack() }}
        key="default-component-profile"
      />

      <DecreasingContainer>
        <Tabs activeStep={activeStep} handleSelectTab={setActiveStep} />

        <Quotes
          data={[0, 1, 2, 3]}
          renderItem={() => <QuoteItem />}
          keyExtractor={(_, index: number) => index.toString()}
          showsVerticalScrollIndicator={false}
        />
      </DecreasingContainer>
    </WrapperPage>
  );
}
