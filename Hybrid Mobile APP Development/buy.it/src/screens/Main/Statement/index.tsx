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
import { QuotesWrapper } from './styles';
import { QuoteItem } from './QuoteItem';
import { ScrollableContent } from '@global/styles';

export function Statement({
  navigation,
}: NativeStackScreenProps<MainRoutes, 'Statement'>) {
  const [activeStep, setActiveStep] = useState<StatusFilterType>();

  return (
    <WrapperPage>
      <ScrollableContent>
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

          <QuotesWrapper>
            {[0, 1, 2, 3].map(item => (
              <QuoteItem key={item} />
            ))}
          </QuotesWrapper>
        </DecreasingContainer>
      </ScrollableContent>
    </WrapperPage>
  );
}
